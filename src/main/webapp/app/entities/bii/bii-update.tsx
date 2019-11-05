import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bii.reducer';
import { IBII } from 'app/shared/model/bii.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBIIUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBIIUpdateState {
  isNew: boolean;
}

export class BIIUpdate extends React.Component<IBIIUpdateProps, IBIIUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { bIIEntity } = this.props;
      const entity = {
        ...bIIEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/bii');
  };

  render() {
    const { bIIEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="bii1TacticalApplicationApp.bII.home.createOrEditLabel">Create or edit a BII</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : bIIEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="bii-id">ID</Label>
                    <AvInput id="bii-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="bii-name">
                    Name
                  </Label>
                  <AvField id="bii-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="typeLabel" for="bii-type">
                    Type
                  </Label>
                  <AvField id="bii-type" type="text" name="type" />
                </AvGroup>
                <AvGroup>
                  <Label id="biiIdLabel" for="bii-biiId">
                    Bii Id
                  </Label>
                  <AvField id="bii-biiId" type="text" name="biiId" />
                </AvGroup>
                <AvGroup>
                  <Label id="detectionTimestampLabel" for="bii-detectionTimestamp">
                    Detection Timestamp
                  </Label>
                  <AvField id="bii-detectionTimestamp" type="string" className="form-control" name="detectionTimestamp" />
                </AvGroup>
                <AvGroup>
                  <Label id="sourceIdLabel" for="bii-sourceId">
                    Source Id
                  </Label>
                  <AvField id="bii-sourceId" type="text" name="sourceId" />
                </AvGroup>
                <AvGroup>
                  <Label id="detectionSystemNameLabel" for="bii-detectionSystemName">
                    Detection System Name
                  </Label>
                  <AvField id="bii-detectionSystemName" type="text" name="detectionSystemName" />
                </AvGroup>
                <AvGroup>
                  <Label id="detectedValueLabel" for="bii-detectedValue">
                    Detected Value
                  </Label>
                  <AvField id="bii-detectedValue" type="text" name="detectedValue" />
                </AvGroup>
                <AvGroup>
                  <Label id="detectionContextLabel" for="bii-detectionContext">
                    Detection Context
                  </Label>
                  <AvField id="bii-detectionContext" type="text" name="detectionContext" />
                </AvGroup>
                <AvGroup>
                  <Label id="etcLabel" for="bii-etc">
                    Etc
                  </Label>
                  <AvField id="bii-etc" type="text" name="etc" />
                </AvGroup>
                <AvGroup>
                  <Label id="etcetcLabel" for="bii-etcetc">
                    Etcetc
                  </Label>
                  <AvField id="bii-etcetc" type="text" name="etcetc" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/bii" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  bIIEntity: storeState.bII.entity,
  loading: storeState.bII.loading,
  updating: storeState.bII.updating,
  updateSuccess: storeState.bII.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BIIUpdate);
